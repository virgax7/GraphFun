package MyGraphs.chapter4dot1.Exercises;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class DifferentApproachesToPowerSet {
    public static void main(String[] args) {
        final List<Integer> set = Arrays.asList(1, 2, 3, 4);
        System.out.println(powerSet1(set));
        System.out.println(powerSet2(set));
        System.out.println(powerSet3(set));
        System.out.println(powerSet4(set));
    }

    private static <T> List<List<T>> powerSet1(final List<T> set) {
        return range(0, 1 << set.size())
                .mapToObj(i -> range(0, set.size()).filter(j -> (i & 1 << j) > 0) .mapToObj(j -> set.get(j)).collect(toList()))
                .collect(toList());
    }

    private static <T> Deque<List<T>> powerSet2(final List<T> set) {
        final Deque<List<T>> result = new ArrayDeque<>();
        powerSet2(result, 0, set, new ArrayList<T>());
        return result;
    }

    private static <T> void powerSet2(final Deque<List<T>> result, final int i, final List<T> set, final List<T> subresult) {
        if (i == set.size()) {
            result.addFirst(new ArrayList<>(subresult));
        } else {
            subresult.add(set.get(i));
            powerSet2(result, i + 1, set, subresult);
            subresult.remove(subresult.size() - 1);
            powerSet2(result, i + 1, set, subresult);
        }
    }

    // this is just simulating the stack of the recursive method powerSet2, just thought it'd be fun... recursion ftw btw cause
    // its here to stay. heh, I should be a rapper.
    private static <T> Deque<List<T>> powerSet3(final List<T> set) {
        if (set.size() < 1) {
            return new ArrayDeque() {{ add(new ArrayList<>()); }};
        }
        final Deque<List<T>> result = new ArrayDeque<>();
        final Deque<Boolean> booleanStack = new ArrayDeque() {{ add(false); add(true); }};
        final Deque<Integer> intStack = new ArrayDeque() {{ add(0); }};
        final List<T> subresult = new ArrayList<>();
        while (!intStack.isEmpty()) {
            int i = intStack.peekLast();
            if (booleanStack.getLast()) {
                updateStacks(booleanStack, intStack, i);
                subresult.add(set.get(i));
            } else {
                intStack.removeLast();
                updateStacks(booleanStack, intStack, i);
                subresult.remove(subresult.size() - 1);
            }
            if (i + 1 == set.size()) {
                addSubresult(result, booleanStack, intStack, subresult);
            }
        }
        return result;
    }

    private static <T> void addSubresult(final Deque<List<T>> result, final Deque<Boolean> booleanStack, final Deque<Integer> intStack, final List<T> subresult) {
        booleanStack.removeLast();
        booleanStack.removeLast();
        intStack.removeLast();
        result.addFirst(new ArrayList<>(subresult));
        return;
    }

    private static void updateStacks(final Deque<Boolean> booleanStack, final Deque<Integer> intStack, final int i) {
        booleanStack.removeLast();
        booleanStack.addLast(false);
        booleanStack.addLast(true);
        intStack.addLast(i + 1);
    }

    private static <T> List<List<T>> powerSet4(final List<T> set) {
        List<List<T>> result = new ArrayList() {{ add(new ArrayList<>()); }};
        for (final T element : set) {
            final List<List<T>> tmpResult = new ArrayList<>();
            for (List<T> subset : result) {
                final List<T> copySet = new ArrayList<>(subset);
                copySet.add(element);
                tmpResult.add(subset);
                tmpResult.add(copySet);
            }
            result = tmpResult;
        }
        return result;
    }

}
