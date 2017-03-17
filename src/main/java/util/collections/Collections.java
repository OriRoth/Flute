package util.collections;

import static util.Cast.supplier;
import static util.Fluent.fluent;
import static util.Initializable.initializable;
import static util.Lazy.lazy;
import static util.Test.equalsNullable;
import static util.mutable.M.mutable;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import util.Initializable;
import util.mutable.M;
import util.mutable.MInteger;

public class Collections {
  public static <K, V> Map.Entry<K, V> entry(K key, V value) {
    return new AbstractMap.SimpleEntry<>(key, value);
  }

  public static <K, V> Map<K, V> map(@SuppressWarnings("unchecked") Map.Entry<K, V>... entries) {
    Map<K, V> ret = new HashMap<>();
    Arrays.stream(entries).forEach(e -> ret.put(e.getKey(), e.getValue()));
    return ret;
  }

  public static <K, V> Map<K, V> factory(Function<K, V> function) {
    Objects.requireNonNull(function);
    return new Map<K, V>() {
      final Function<K, V> f = lazy(function);
      Map<K, V> defined = new HashMap<>();
      boolean cleared;

      @Override
      public int size() {
        return cleared ? defined.size() : Integer.MAX_VALUE;
      }

      @Override
      public boolean isEmpty() {
        return cleared && defined.isEmpty();
      }

      @Override
      public boolean containsKey(Object key) {
        return !cleared || defined.containsKey(key);
      }

      @Override
      public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
      }

      @SuppressWarnings("unchecked")
      @Override
      public V get(Object key) {
        return cleared ? defined.get(key) : f.apply((K) key);
      }

      @Override
      public V put(K key, V value) {
        return fluent(defined.put(key, value)).d0(__ -> f.apply(key)).origin();
      }

      @SuppressWarnings("unchecked")
      @Override
      public V remove(Object key) {
        return defined.replace((K) key, null);
      }

      @Override
      public void putAll(Map<? extends K, ? extends V> map) {
        defined.putAll(map);
      }

      @Override
      public void clear() {
        cleared = true;
        defined.clear();
      }

      @Override
      public Set<K> keySet() {
        return !cleared ? all() : defined.keySet();
      }

      @Override
      public Collection<V> values() {
        throw new UnsupportedOperationException();
      }

      @Override
      public Set<java.util.Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
      }

      @Override
      public String toString() {
        return !cleared ? "{...}" : defined.toString();
      }
    };
  }

  public static <T> Set<T> all() {
    return new Set<T>() {
      Set<T> defined;
      Set<T> undefined = new HashSet<>();
      boolean cleared;

      @Override
      public int size() {
        return cleared ? defined.size() : Integer.MAX_VALUE;
      }

      @Override
      public boolean isEmpty() {
        return cleared && defined.isEmpty();
      }

      @SuppressWarnings({ "unused", "unchecked" })
      @Override
      public boolean contains(Object o) {
        T t = (T) o; // throws an exception
        return cleared ? defined.contains(o) : !undefined.contains(o);
      }

      @Override
      public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
      }

      @Override
      public Object[] toArray() {
        throw new UnsupportedOperationException();
      }

      @Override
      public <X> X[] toArray(X[] a) {
        throw new UnsupportedOperationException();
      }

      @Override
      public boolean add(T e) {
        return cleared ? defined.add(e) : undefined.remove(e);
      }

      @SuppressWarnings("unchecked")
      @Override
      public boolean remove(Object o) {
        return cleared ? defined.remove(o) : undefined.add((T) o);
      }

      @Override
      public boolean containsAll(Collection<?> c) {
        return cleared ? defined.containsAll(c) //
            // "c" then "undefined"!
            : java.util.Collections.disjoint(c, undefined);
      }

      @Override
      public boolean addAll(Collection<? extends T> c) {
        return cleared ? defined.addAll(c) : undefined.removeAll(c);
      }

      @SuppressWarnings("unchecked")
      @Override
      public boolean retainAll(Collection<?> c) {
        if (cleared)
          return defined.retainAll(c);
        cleared = true;
        defined = new HashSet<>();
        defined.addAll((Collection<? extends T>) c);
        defined.removeAll(undefined);
        undefined = null;
        return true;
      }

      @SuppressWarnings("unchecked")
      @Override
      public boolean removeAll(Collection<?> c) {
        return cleared ? defined.removeAll(c) //
            : undefined.addAll((Collection<? extends T>) c);
      }

      @Override
      public void clear() {
        if (cleared)
          defined.clear();
        else {
          cleared = true;
          defined = new HashSet<>();
          undefined = null;
        }
      }

      @Override
      public String toString() {
        return !cleared ? "[...]" : defined.toString();
      }
    };
  }

  public static <T> Iterable<T> range(Supplier<T> supplier) {
    return range(supplier, null);
  }

  public static <T> Iterable<T> range(Supplier<T> supplier, T stop) {
    return new Iterable<T>() {
      @Override
      public Iterator<T> iterator() {
        return new Iterator<T>() {
          M<Initializable<T>> next = mutable(initializable());

          @Override
          public boolean hasNext() {
            return !equalsNullable(next.get().suggest(supplier), stop);
          }

          @Override
          public T next() {
            return fluent(next.get().suggest(supplier), __ -> next.set(initializable()));
          }
        };
      }
    };
  }

  public static Iterable<Integer> range(int start, int end) {
    return range(fluent(new MInteger(start)) //
        .lane(m -> supplier(() -> fluent(m.intValue(), v -> m.set(v + 1)))).origin(), end);
  }
}
