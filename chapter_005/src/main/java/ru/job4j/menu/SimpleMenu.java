package ru.job4j.menu;

import java.util.*;

public class SimpleMenu implements Menu {
    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean result = false;
        boolean root = Objects.equals(parentName, ROOT);
        if (root) {
            result = rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        }
        if (!root) {
            Optional<ItemInfo> parentItemInfo = findItem(parentName);
            if (parentItemInfo.isPresent()) {
                result = parentItemInfo.get().menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
            }
        }
        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(itemInfo -> new MenuItemInfo(itemInfo.menuItem, itemInfo.number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<>() {
            private final DFSIterator dfsIterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                ItemInfo next = dfsIterator.next();
                return new MenuItemInfo(next.menuItem, next.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        ItemInfo foundItem = null;
        var it = new DFSIterator();
        while (it.hasNext()) {
            ItemInfo itemInfo = it.next();
            if (itemInfo.menuItem.getName().equals(name)) {
                foundItem = itemInfo;
                break;
            }
        }
        return Optional.ofNullable(foundItem);
    }

    /**
     * Реализация элементов меню.
     */
    private static class SimpleMenuItem implements MenuItem {
        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    /**
     * Итератор для прохождения по пунктам меню.
     */
    private class DFSIterator implements Iterator<ItemInfo> {
        Deque<MenuItem> stack = new LinkedList<>();
        Deque<String> numbers = new LinkedList<>();

        public DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            var iterator = children.listIterator(children.size());
            while (iterator.hasPrevious()) {
                stack.addFirst(iterator.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--).concat(".")));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    /**
     * Класс для компановки пункта меню и номер пункта.
     */
    private static class ItemInfo {
        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
