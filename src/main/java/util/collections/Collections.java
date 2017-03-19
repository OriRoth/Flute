package util.collections;

import static util.Initializable.initializable;
import static util.Lazy.lazy;
import static util.Test.equalsNullable;
import static util.fluent.Fluent.fluent;
import static util.fluent.FluentWrapper.fluent;
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
import util.Lazy;
import util.mutable.M;
import util.mutable.MInteger;

/**
 * Collections utilities.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 */
public class Collections {
  /**
   * Easy map entry creation using {@link AbstractMap.SimpleEntry}.
   * 
   * @param key
   *          JD
   * @param value
   *          JD
   * @return entry from key and value
   */
  public static <K, V> Map.Entry<K, V> entry(K key, V value) {
    return new AbstractMap.SimpleEntry<>(key, value);
  }

  /**
   * Easy map creation using entries.
   * 
   * @param entries
   *          JD
   * @return map from entries
   */
  public static <K, V> Map<K, V> map(@SuppressWarnings("unchecked") Map.Entry<K, V>... entries) {
    Map<K, V> ret = new HashMap<>();
    Arrays.stream(entries).forEach(e -> ret.put(e.getKey(), e.getValue()));
    return ret;
  }

  /**
   * Easy map creation using entries.
   * 
   * @param entries
   *          JD
   * @return map from entries
   */
  public static <K, V> Map<K, V> map(Iterable<Map.Entry<K, V>> entries) {
    Map<K, V> ret = new HashMap<>();
    entries.forEach(e -> ret.put(e.getKey(), e.getValue()));
    return ret;
  }

  /**
   * Converts a {@link Function} to a {@link Map}. The map contains keys from
   * input {@link Iterable}, and the values are calculated by the input
   * function.
   * 
   * @param function
   *          calculates values
   * @param keys
   *          all keys
   * @return map containing keys by the iterable and values by the function
   */
  public static <K, V> Map<K, V> map(Function<K, V> function, Iterable<K> keys) {
    Map<K, V> ret = new HashMap<>();
    keys.forEach(k -> ret.put(k, function.apply(k)));
    return ret;
  }

  /**
   * Converts a {@link Function} to a {@link Map}. The returned map declares
   * itself to contain all keys, and uses the input function to calculate values
   * lazily. Unsupported methods: {@link Map#containsValue(Object)},
   * {@link Map#values()}, {@link Map#entrySet()}.
   * 
   * @param function
   *          JD
   * @return a map calculated by the function
   * @see Lazy#lazy(Function)
   */
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

  /**
   * Creates a set that contains all values. Unsupported methods:
   * {@link Set#iterator()}, {@link Set#toArray()},
   * {@link Set#toArray(Object[])}.
   * 
   * @return
   */
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

  /**
   * Creates an {@link Iterable} whose values are evaluated by a
   * {@link Supplier}, until null is received.
   * 
   * @param supplier
   *          content evaluetor
   * @return iterable from supplier
   * @see #range(Supplier, Object)
   */
  public static <T> Iterable<T> range(Supplier<T> supplier) {
    return range(supplier, null);
  }

  /**
   * Creates an {@link Iterable} whose values are evaluated by a
   * {@link Supplier}, until a certain object is received. This stop object
   * would not be included in resulting iterable.
   * 
   * @param supplier
   *          content evaluetor
   * @param stop
   *          on which supplier evaluation will stop
   * @return iterable from supplier
   */
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

  /**
   * @param start
   *          range start
   * @param end
   *          range end
   * @return integer iterable from start to end (not include)
   * @see #range(Supplier, Object)
   */
  public static Iterable<Integer> range(int start, int end) {
    return range((Supplier<Integer>) fluent(new MInteger(start)) //
        .lane(m -> (Supplier<Integer>) () -> fluent(m.intValue(), v -> m.set(v + 1))), end);
  }

  /**
   * @param t
   *          an object
   * @return a single value collection containing this object
   */
  public static <T> Collection<T> asCollection(T t) {
    return Arrays.asList(t);
  }
}
