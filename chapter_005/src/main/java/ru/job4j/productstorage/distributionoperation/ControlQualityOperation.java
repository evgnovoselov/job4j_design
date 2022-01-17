package ru.job4j.productstorage.distributionoperation;

import ru.job4j.productstorage.storage.Storage;

public interface ControlQualityOperation {
    Storage getTo();

    void toDistribute(Storage from);
}
