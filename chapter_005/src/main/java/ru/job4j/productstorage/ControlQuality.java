package ru.job4j.productstorage;

import ru.job4j.productstorage.distributionoperation.DistributionOperation;
import ru.job4j.productstorage.storage.Storage;

import java.util.List;

/**
 * Контроллер правил распределения продуктов.
 */
public class ControlQuality {
    private List<DistributionOperation> operations;

    public ControlQuality(List<DistributionOperation> operations) {
        this.operations = operations;
    }

    /**
     * Распределение продуктов по хранилищам.
     *
     * @param storages Список хранилещей для распределения продуктов.
     */
    public void distributeFoodsIn(List<Storage> storages) {
        for (Storage storage : storages) {
            for (DistributionOperation operation : operations) {
                operation.toDistribute(storage);
            }
        }
    }
}
