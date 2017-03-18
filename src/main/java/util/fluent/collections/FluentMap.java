package util.fluent.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import util.FluentAPI;
import util.fluent.FluentWrapper;

@SuppressWarnings("unchecked")
@FluentAPI
public class FluentMap<K, V> extends FluentWrapper<Map<K, V>> {
  public FluentMap() {
    super(new HashMap<>());
  }

  public FluentMap(Map<K, V> map) {
    super(map);
  }

  @FluentAPI
  public static <K, V> FluentMap<K, V> fluentMap(Map<K, V> map) {
    return new FluentMap<>(map);
  }

  @FluentAPI
  public FluentMap<K, V> clear() {
    return d0(m -> m.clear());
  }

  @FluentAPI
  public FluentMap<K, V> put(K key, V value) {
    return d0(m -> m.put(key, value));
  }

  @FluentAPI
  public FluentMap<K, V> putAll(Map<? extends K, ? extends V> map) {
    return d0(m -> m.putAll(map));
  }

  public V get(Object key) {
    return origin().get(key);
  }

  public int size() {
    return origin().size();
  }

  public boolean isEmpty() {
    return origin().isEmpty();
  }

  public boolean containsKey(Object key) {
    return origin().containsKey(key);
  }

  @FluentAPI
  public FluentMap<K, V> remove(Object key) {
    return d0(m -> m.remove(key));
  }

  @FluentAPI
  public FluentMap<K, V> ifContainsKey(K key) {
    return validate(m -> m.containsKey(key));
  }

  @FluentAPI
  public FluentMap<K, V> ifNotContainsKey(K key) {
    return validate(m -> !m.containsKey(key));
  }

  @FluentAPI
  public FluentMap<K, V> ifContainsValue(V value) {
    return validate(m -> m.containsValue(value));
  }

  @FluentAPI
  public FluentMap<K, V> ifNotContainsValue(V value) {
    return validate(m -> !m.containsValue(value));
  }

  @Override
  public FluentMap<K, V> d0(Consumer<Map<K, V>> action) {
    return (FluentMap<K, V>) super.d0(action);
  }
  
  @Override
  public FluentMap<K, V> whileD0(Predicate<Map<K, V>> condition, Consumer<Map<K, V>> action) {
    return (FluentMap<K, V>) super.whileD0(condition, action);
  }
  
  @Override
  public FluentMap<K, V> d0While(Consumer<Map<K, V>> action, Predicate<Map<K, V>> condition) {
    return (FluentMap<K, V>) super.d0While(action, condition);
  }

  @Override
  public FluentMap<K, V> validate(Predicate<Map<K, V>> predicate) {
    return (FluentMap<K, V>) super.validate(predicate);
  }

  @Override
  public FluentMap<K, V> elze() {
    return (FluentMap<K, V>) super.elze();
  }
}
