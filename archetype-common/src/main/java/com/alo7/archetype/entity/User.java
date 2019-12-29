// Copyright 2018 Alo7 Inc. All rights reserved.

package com.alo7.archetype.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author archetype on 2018/9/30.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userName;
    private Long id;
}