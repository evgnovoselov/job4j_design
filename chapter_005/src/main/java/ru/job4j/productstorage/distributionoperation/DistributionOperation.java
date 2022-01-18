package ru.job4j.productstorage.distributionoperation;

import ru.job4j.productstorage.storage.Storage;

/**
 * Distribution operations
 */
public interface DistributionOperation {
    Storage getTo();

    void toDistribute(Storage from);
}
