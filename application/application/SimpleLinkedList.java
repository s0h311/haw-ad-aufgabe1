package application;

public class SimpleLinkedList<T> implements SimpleList<T> {

  private int elements;

  @Override
  public T extractNo(int requestedPosition) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'extractNo'");
  }

  @Override
  public T getNo(int requestedPosition) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getNo'");
  }

  @Override
  public T setNo(int requestedPosition, T value) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setNo'");
  }

  @Override
  public void putNo(int requestedPosition, T value) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'putNo'");
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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'clear'");
  }
  
}
