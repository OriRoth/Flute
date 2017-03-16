package util.collections;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

import util.Fluent;

@SuppressWarnings("unchecked")
public class FluentHashMap<K, V> extends Fluent<HashMap<K, V>> {
  public FluentHashMap() {
    super(new HashMap<>());
  }

  public FluentHashMap(HashMap<K, V> map) {
    super(map);
  }

  public static <K, V> FluentHashMap<K, V> fluentMap(HashMap<K, V> map) {
    return new FluentHashMap<>(map);
  }

  public FluentHashMap<K, V> clear() {
    return d0(m -> m.clear());
  }

  public FluentHashMap<K, V> put(K key, V value) {
    return d0(m -> m.put(key, value));
  }

  public V get(K key) {
    return origin().get(key);
  }
  
  public int size() {
    return origin().size();
  }

  public FluentHashMap<K, V> ifContainsKey(K key) {
    return validate(m -> m.containsKey(key));
  }

  public FluentHashMap<K, V> ifNotContainsKey(K key) {
    return validate(m -> !m.containsKey(key));
  }

  public FluentHashMap<K, V> ifContainsValue(V value) {
    return validate(m -> m.containsValue(value));
  }

  public FluentHashMap<K, V> ifNotContainsValue(V value) {
    return validate(m -> !m.containsValue(value));
  }

  @Override
  public FluentHashMap<K, V> d0(Consumer<HashMap<K, V>> action) {
    return (FluentHashMap<K, V>) super.d0(action);
  }

  @Override
  public FluentHashMap<K, V> validate(Predicate<HashMap<K, V>> predicate) {
    return (FluentHashMap<K, V>) super.validate(predicate);
  }
}
