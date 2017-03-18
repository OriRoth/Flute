package util.fluent.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import util.fluent.Fluent;

public class FluentSet<T> extends Fluent<Set<T>> {
  public FluentSet() {
    super(new HashSet<>());
  }

  public FluentSet(Set<T> set) {
    super(set);
  }

  public static <T> FluentSet<T> fluentSet(Set<T> set) {
    return new FluentSet<>(set);
  }

  public FluentSet<T> add(T t) {
    return d0(s -> s.add(t));
  }

  public FluentSet<T> addAll(Collection<? extends T> collection) {
    return d0(s -> s.addAll(collection));
  }

  public FluentSet<T> remove(Object t) {
    return d0(s -> s.remove(t));
  }

  public FluentSet<T> removeAll(Collection<? extends T> collection) {
    return d0(s -> s.removeAll(collection));
  }

  public int size() {
    return origin().size();
  }
  
  public boolean isEmpty() {
    return origin().isEmpty();
  }
  
  public boolean contains(Object o) {
    return origin().contains(o);
  }
  
  public boolean containsAll(Collection<?> c) {
    return origin().containsAll(c);
  }
  
  public FluentSet<T> clear() {
    return d0(s -> s.clear());
  }

  public FluentSet<T> ifContains(T t) {
    return validate(s -> s.contains(t));
  }

  public FluentSet<T> ifNotContains(T t) {
    return validate(s -> !s.contains(t));
  }

  @Override
  public FluentSet<T> d0(Consumer<Set<T>> action) {
    return (FluentSet<T>) super.d0(action);
  }

  @Override
  public FluentSet<T> validate(Predicate<Set<T>> predicate) {
    return (FluentSet<T>) super.validate(predicate);
  }
  
  @Override
  public FluentSet<T> elze() {
    return (FluentSet<T>) super.elze();
  }
}
