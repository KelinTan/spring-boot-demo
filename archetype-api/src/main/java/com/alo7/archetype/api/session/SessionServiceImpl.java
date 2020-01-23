// Copyright 2019 Alo7 Inc. All rights reserved.

package com.alo7.archetype.api.session;

import com.alo7.archetype.base.jwt.JwtManager;
import com.alo7.archetype.common.entity.biz.BizAccount;
import com.alo7.archetype.common.mapper.biz.BizAccountMapper;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kelin Tan
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SessionServiceImpl implements SessionService {
    private static final String AUTHORIZATION_SCHEME = "Bearer ";

    private final JwtManager jwtManager;
    private final BizAccountMapper accountMapper;

    @Override
    public BizAccount getCurrentAccount(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(header) || !header.startsWith(AUTHORIZATION_SCHEME)) {
            throw SessionExceptionFactory.toAccountSessionExpired();
        }

        try {
            DecodedJWT jwt = jwtManager.verify(header.substring(AUTHORIZATION_SCHEME.length()));
            if (jwt != null) {
                BizAccount account = accountMapper.findByAccount(jwt.getSubject());
                if (account != null) {
                    SessionCache.JWT.set(jwt);
                    return account;
                }
            }
        } catch (Exception e) {
            log.error("jwt verify error:", e);
        }
        throw SessionExceptionFactory.toAccountSessionExpired();
    }
}