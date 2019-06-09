package com.mengla.bookstore.dao;

import com.mengla.bookstore.model.PackageList;

public interface IPackageListDao {
    /**
     * 增加包裹
     * @param packageList
     * @return
     */
    int insertPackageList(PackageList packageList);

    /**
     * 删除包裹
     * @param packageId
     * @return
     */
    int deletePackageList(long packageId);
}
