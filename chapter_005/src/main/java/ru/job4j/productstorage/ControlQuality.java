package ru.job4j.productstorage;

import ru.job4j.productstorage.distributionoperation.DistributionOperation;
import ru.job4j.productstorage.storage.Storage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер правил распределения продуктов.
 */
public class ControlQuality {
    private List<DistributionOperation> operations = new ArrayList<>();

    public ControlQuality(List<DistributionOperation> operations) {
        this.operations = operations;
    }

    /**
     * Распределение продуктов по хранилищам.
     *
     * @param storages Список хранилещей для распределения продуктов.
     */
    public void distributeFoods(List<Storage> storages) {
        for (Storage storage : storages) {
            for (DistributionOperation operation : operations) {
                if (!storage.equals(operation.getTo())) {
                    operation.toDistribute(storage);
                }
            }
        }
    }
}
