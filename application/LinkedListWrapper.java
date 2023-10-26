package application;

import java.util.LinkedList;

public class LinkedListWrapper<T> implements SimpleList<T> {

  private LinkedList<T> list;

  public LinkedListWrapper(){
    list = new LinkedList<T>();
  }

  @Override
  public T extractNo(int requestedPosition) {
    return list.remove(requestedPosition);
  }

  @Override
  public T getNo(int requestedPosition) {
    return list.get(requestedPosition);
  }

  @Override
  public T setNo(int requestedPosition, T value) {
    return list.set(requestedPosition, value);
  }

  @Override
  public void putNo(int requestedPosition, T value) {
    list.add(requestedPosition, value);
  }

  @Override
  public int getSize() {
    return list.size();
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public void clear() {
    list.clear();
  }

}
