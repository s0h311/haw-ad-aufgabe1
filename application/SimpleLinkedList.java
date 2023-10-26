public class SimpleLinkedList<T> implements SimpleList<T> {

  private int elements;
  private Node<T> head;

  public SimpleLinkedList() {
    head = new Node<T>();
  }

  @Override
  public T extractNo(int requestedPosition) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'extractNo'");
  }

  @Override
  public T getNo(int requestedPosition) {
    if (requestedPosition > elements) {
      throw new IllegalArgumentException();
    }
    Node<T> requestedNode = head;
    for (int i = 0; i <= requestedPosition; i++) {
      requestedNode = requestedNode.next;
    }
    return requestedNode.data;
  }

  @Override
  public T setNo(int requestedPosition, T value) {
    if (requestedPosition > elements) {
      throw new IllegalArgumentException();
    }
    Node<T> newNode = new Node<T>(value);
    Node<T> node = head;
    Node<T> oldNode = head;
    for (int i = 0; i < requestedPosition; i++) {
      node = node.next;
      oldNode = node;
      node.next = newNode;
    }
    return oldNode.data;
  }

  @Override
  public void putNo(int requestedPosition, T value) {
    if (requestedPosition > elements) {
      throw new IllegalArgumentException();
    }
    Node<T> newNode = new Node<T>(value);
    Node<T> node = head;
    for (int i = 0; i < requestedPosition; i++) {
      node = node.next;
      node.next = newNode;
    }
  }

  @Override
  public int getSize() {
    return elements;
  }

  @Override
  public boolean isEmpty() {
    return elements == 0;
  }

  @Override
  public void clear() {
    head = null;
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
