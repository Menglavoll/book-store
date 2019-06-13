package com.mengla.bookstore.service.impl;

import com.mengla.bookstore.dao.IPackageListDao;
import com.mengla.bookstore.model.PackageList;
import com.mengla.bookstore.service.IPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PackageService implements IPackageService {

    @Autowired
    private IPackageListDao packageListDao;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int insertPackageList(PackageList packageList) {
        return packageListDao.insertPackageList(packageList);
    }

    @Override
    public int deletePackageList(long packageId) {
        return packageListDao.deletePackageList(packageId);
    }

    @Override
    public List<PackageList> findPackageByOrder(int orderPackage) {
        return packageListDao.findPackageByOrder(orderPackage);
    }
}
