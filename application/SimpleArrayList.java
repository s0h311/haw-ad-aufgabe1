package application;

public class SimpleArrayList<T> implements SimpleList<T> {

  private T[] array; // public
  private int size; // public

  public SimpleArrayList() {
    array = getNewArray(64);
  }

  private void checkArrayContract() {
    if (size < 64 && array.length == 64) return;
    if ((double) array.length / (double) size < 2.0) return;
    int deltaDecrease = array.length % 3 == 0 ? 0 : array.length % 3 == 1 ? 2 : 1;
    int newSize = (array.length + deltaDecrease) * 2 / 3;
    if (newSize < 64) newSize = 64;

    T[] newArray = getNewArray(newSize);

    System.arraycopy(array, 0, newArray, 0, newSize);
    array = newArray;
  }

  private void checkArrayExpand() {
    if (Math.sqrt(array.length - size) > 1) return;
    int deltaIncrease = array.length % 2 == 0 ? 0 : 1;
    int newSize = array.length + (array.length + deltaIncrease) / 2;
    T[] newArray = getNewArray(newSize);
    System.arraycopy(array, 0, newArray, 0, array.length);
    array = newArray;
  }

  @Override
  public T extractNo(int requestedPosition) {
    checkPosition(requestedPosition);

    T res = array[requestedPosition];

    for (int i = requestedPosition; i < size - 1; i++) {
      array[i] = array[i + 1];
    }

    array[size - 1] = null;

    size--;
    checkArrayContract();

    return res;
  }

  @Override
  public T getNo(int requestedPosition) {
    if (requestedPosition < 0 || requestedPosition > size - 1) {
      throw new IllegalArgumentException("invalid position");
    }

    return array[requestedPosition];
  }

  @Override
  public T setNo(int requestedPosition, T value) {
    checkPosition(requestedPosition);

    T old = array[requestedPosition];
    array[requestedPosition] = value;
    return old;
  }

  @Override
  public void putNo(int requestedPosition, T value) {
    checkPosition(requestedPosition);
    checkArrayExpand();

    for (int i = size; i > requestedPosition; i--) {
      array[i] = array[i - 1];
    }

    array[requestedPosition] = value;
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
    array = getNewArray(64);
    size = 0;
  }

  // HELPERS //
  private T[] getNewArray(int size) {
    return (T[]) new Object[size];
  }

  private void checkPosition(int requestedPosition) {
    if (requestedPosition < 0 || requestedPosition > size) {
      throw new IllegalArgumentException("invalid position");
    }
  }

}
