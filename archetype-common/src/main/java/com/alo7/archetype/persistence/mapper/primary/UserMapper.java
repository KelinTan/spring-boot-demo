// Copyright 2019 Alo7 Inc. All rights reserved.

package com.alo7.archetype.persistence.mapper.primary;

import com.alo7.archetype.mybatis.crud.BasicCrudMapper;
import com.alo7.archetype.mybatis.crud.MapperTable;
import com.alo7.archetype.persistence.entity.primary.User;

import java.util.List;

/**
 * @author Kelin Tan
 */
@MapperTable(value = "user", columns = "id,user_name")
public interface UserMapper extends BasicCrudMapper<User> {
    List<User> findByName(String name);
}