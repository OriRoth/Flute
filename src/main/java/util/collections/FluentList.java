package util.collections;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import util.Fluent;

public class FluentList<T> extends Fluent<List<T>> {
  public FluentList() {
    super(new LinkedList<>());
  }

  public FluentList(List<T> object) {
    super(object);
  }
  
  public static <T> FluentList<T> fluent(List<T> list) {
    return new FluentList<>(list);
  }
  
  public FluentList<T> add(T t) {
    return d0(l -> l.add(t));
  }
  
  public FluentList<T> addAll(Collection<? extends T> collection) {
    return d0(l -> l.addAll(collection));
  }
  
  public FluentList<T> remove(T t) {
    return d0(l -> l.remove(t));
  }
  
  public FluentList<T> removeAll(Collection<? extends T> collection) {
    return d0(l -> l.removeAll(collection));
  }
  
  public int size() {
    return origin().size();
  }
  
  public T get(int index) {
    return origin().get(index);
  }
  
  public FluentList<T> ifEmpty() {
    return validate(l -> l.isEmpty());
  }
  
  public FluentList<T> ifNotEmpty() {
    return validate(l -> !l.isEmpty());
  }
  
  public FluentList<T> ifContains(T t) {
    return validate(l -> l.contains(t));
  }
  
  public FluentList<T> ifNotContains(T t) {
    return validate(l -> !l.contains(t));
  }
  
  public FluentList<T> ifContainsAll(Collection<? extends T> collection) {
    return validate(l -> l.containsAll(collection));
  }
  
  public FluentList<T> ifNotContainsAll(Collection<? extends T> collection) {
    return validate(l -> !l.containsAll(collection));
  }
  
  @Override
  public FluentList<T> d0(Consumer<List<T>> action) {
    return (FluentList<T>) super.d0(action);
  }
  
  @Override
  public FluentList<T> validate(Predicate<List<T>> predicate) {
    return (FluentList<T>) super.validate(predicate);
  }
}
