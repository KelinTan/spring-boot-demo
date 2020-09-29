// Copyright 2020 Kelin Inc. All rights reserved.

package com.kelin.archetype.common.database;

/**
 * @author Kelin Tan
 */
public class DbFactory {
    public static String computeDataSourceName(String name) {
        return name + ".dataSource";
    }

    public static String computeSqlSessionFactoryName(String name) {
        return name + ".sqlSessionFactory";
    }

    public static String computeSqlSessionTemplateName(String name) {
        return name + ".sqlSessionTemplate";
    }

    public static String computeTransactionManagerName(String name) {
        return name + ".transactionManager";
    }
}
