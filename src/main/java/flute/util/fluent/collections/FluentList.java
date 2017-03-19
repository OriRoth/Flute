package flute.util.fluent.collections;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import flute.util.Decorator;
import flute.util.FluentAPI;
import flute.util.fluent.FluentWrapper;

@FluentAPI
public class FluentList<T> extends FluentWrapper<List<T>> {
  public FluentList() {
    super(new LinkedList<>());
  }

  public FluentList(List<T> object) {
    super(object);
  }

  @FluentAPI
  @Decorator
  public static <T> FluentList<T> fluent(List<T> list) {
    return new FluentList<>(list);
  }

  @FluentAPI
  public FluentList<T> add(T t) {
    return d0(l -> l.add(t));
  }

  @FluentAPI
  public FluentList<T> add(int index, T t) {
    return d0(l -> l.add(index, t));
  }

  @FluentAPI
  public FluentList<T> addAll(Collection<? extends T> collection) {
    return d0(l -> l.addAll(collection));
  }

  @FluentAPI
  public FluentList<T> remove(T t) {
    return d0(l -> l.remove(t));
  }

  @FluentAPI
  public FluentList<T> removeAll(Collection<? extends T> collection) {
    return d0(l -> l.removeAll(collection));
  }

  @FluentAPI
  public FluentList<T> set(int index, T element) {
    return d0(l -> l.set(index, element));
  }

  @FluentAPI
  public FluentList<T> clear() {
    return d0(l -> l.clear());
  }

  @FluentAPI
  public FluentList<T> forEach(Consumer<? super T> action) {
    return d0(l -> l.forEach(action));
  }

  public int size() {
    return origin().size();
  }

  public T get(int index) {
    return origin().get(index);
  }

  public Object[] toArray() {
    return origin().toArray();
  }

  public <X> X[] toArray(X[] a) {
    return origin().toArray(a);
  }

  public boolean containsAll(Collection<?> c) {
    return origin().containsAll(c);
  }

  @FluentAPI
  public FluentList<T> ifEmpty() {
    return validate(l -> l.isEmpty());
  }

  @FluentAPI
  public FluentList<T> ifNotEmpty() {
    return validate(l -> !l.isEmpty());
  }

  @FluentAPI
  public FluentList<T> ifContains(T t) {
    return validate(l -> l.contains(t));
  }

  @FluentAPI
  public FluentList<T> ifNotContains(T t) {
    return validate(l -> !l.contains(t));
  }

  @FluentAPI
  public FluentList<T> ifContainsAll(Collection<? extends T> collection) {
    return validate(l -> l.containsAll(collection));
  }

  @FluentAPI
  public FluentList<T> ifNotContainsAll(Collection<? extends T> collection) {
    return validate(l -> !l.containsAll(collection));
  }

  @Override
  public FluentList<T> d0(Consumer<List<T>> action) {
    return (FluentList<T>) super.d0(action);
  }

  @Override
  public FluentList<T> whileD0(Predicate<List<T>> condition, Consumer<List<T>> action) {
    return (FluentList<T>) super.whileD0(condition, action);
  }

  @Override
  public FluentList<T> d0While(Consumer<List<T>> action, Predicate<List<T>> condition) {
    return (FluentList<T>) super.d0While(action, condition);
  }

  @Override
  public FluentList<T> validate(Predicate<List<T>> predicate) {
    return (FluentList<T>) super.validate(predicate);
  }

  @Override
  public FluentList<T> elze() {
    return (FluentList<T>) super.elze();
  }
}
