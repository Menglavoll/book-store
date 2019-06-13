package com.mengla.bookstore.service;

import com.mengla.bookstore.model.PackageList;

import java.util.List;

public interface IPackageService {
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

    List<PackageList> findPackageByOrder(int orderPackage);
}
