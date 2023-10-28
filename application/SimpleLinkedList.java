package application;

public class SimpleLinkedList<T> implements SimpleList<T> {

  private Node<T> head;
  private int size;

  public SimpleLinkedList() {
    head = new Node<>();
    size = 0;
  }

  @Override
  public T extractNo(int requestedPosition) {
    checkPosition(requestedPosition);

    Node<T> current = head;

    for (int i = 0; i < requestedPosition; i++) {
      current = current.next;
    }

    size--;
    return current.data;
  }

  @Override
  public T getNo(int requestedPosition) {
    checkPosition(requestedPosition);

    Node<T> requestedNode = head;

    for (int i = 0; i < requestedPosition; i++) {
      requestedNode = requestedNode.next;
    }

    return requestedNode.data;
  }

  @Override
  public T setNo(int requestedPosition, T value) {
    checkPosition(requestedPosition);

    Node<T> newNode = new Node<>(value);

    if (requestedPosition == 0) {
      Node<T> old = head;
      newNode.next = head.next;
      head = newNode;
      return old.data;
    }

    Node<T> current = head;

    for (int i = 0; i < requestedPosition - 1; i++) {
      current = current.next;
    }

    Node<T> next = current.next;
    current.next = newNode;
    newNode.next = next != null ? next.next : null;

    return next != null ? next.data : null;
  }

  @Override
  public void putNo(int requestedPosition, T value) {
    if (requestedPosition > size) {
      throw new IllegalArgumentException("invalid position");
    }

    Node<T> newNode = new Node<>(value);

    if (requestedPosition == 0 && size == 0) {
      head = newNode;
      size++;
      return;
    }

    if (requestedPosition == 0 && head.next != null) {
      newNode.next = head;
      head = newNode;
      size++;
      return;
    }

    Node<T> current = head;

    for (int i = 0; i < requestedPosition; i++) {
      if (current.next != null) {
        current = current.next;
      }
    }

    if (requestedPosition < size - 1) {
      Node<T> next = current.next;
      current.next = newNode;
      newNode.next = next;
      size++;
      return;
    }

    current.next = newNode;
    size++;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void clear() {
    head = null;
  }

  // HELPERS

  private void checkPosition(int requestedPosition) {
    if (requestedPosition >= size) {
      throw new IllegalArgumentException("invalid position");
    }
  }

  private class Node<T> {
    Node<T> next;
    T data;

    Node() {
    }

    Node(T data) {
      this.data = data;
    }
  }

}
