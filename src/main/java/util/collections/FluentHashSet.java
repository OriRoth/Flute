package util.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Predicate;

import util.Fluent;

public class FluentHashSet<T> extends Fluent<HashSet<T>> {
  public FluentHashSet() {
    super(new HashSet<>());
  }

  public FluentHashSet(HashSet<T> set) {
    super(set);
  }

  public static <T> FluentHashSet<T> fluent(HashSet<T> set) {
    return new FluentHashSet<>(set);
  }

  public FluentHashSet<T> add(T t) {
    return d0(s -> s.add(t));
  }

  public FluentHashSet<T> addAll(Collection<? extends T> collection) {
    return d0(s -> s.addAll(collection));
  }

  public FluentHashSet<T> remove(T t) {
    return d0(s -> s.remove(t));
  }

  public FluentHashSet<T> removeAll(Collection<? extends T> collection) {
    return d0(s -> s.removeAll(collection));
  }

  public int size() {
    return origin().size();
  }

  public FluentHashSet<T> ifContains(T t) {
    return validate(s -> s.contains(t));
  }

  public FluentHashSet<T> ifNotContains(T t) {
    return validate(s -> !s.contains(t));
  }

  @Override
  public FluentHashSet<T> d0(Consumer<HashSet<T>> action) {
    return (FluentHashSet<T>) super.d0(action);
  }

  @Override
  public FluentHashSet<T> validate(Predicate<HashSet<T>> predicate) {
    return (FluentHashSet<T>) super.validate(predicate);
  }
}
