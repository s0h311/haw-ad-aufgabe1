package application;

public class SimpleArrayList<T> implements SimpleList<T> {

  private T[] array;
  private int size;

  public SimpleArrayList() {
    array = getNewArray(64);
  }

  /**
   * Array Größe wird vergrößert oder verkleinert, je nach bedarf.
   */
  private void checkArraySize(int requestedPosition) {
    if (requestedPosition > array.length) {
      int newSize = array.length;
      if (array.length % 2 != 0) {
        newSize += (newSize + 1) / 2;
      } else {
        newSize += newSize / 2;
      }
      T[] biggerArray = getNewArray(newSize);
      System.arraycopy(array, 0, biggerArray, 0, array.length);
    } else if (array.length > 64 || array.length >= size * 2) {
      int newSize = 2 * array.length / 3;
      T[] smallerArray = getNewArray(newSize);
      System.arraycopy(array, 0, smallerArray, 0, array.length);
    }
  }

  private void checkArrayContract() {
    if ((double) array.length / (double) size > 2.0) return;
    int deltaDecrease = array.length % 3 == 0 ? 0 : array.length % 3 == 1 ? 2 : 1;
    int newSize = (array.length + deltaDecrease) * 2 / 3;
    T[] newArray = getNewArray(newSize);
    System.arraycopy(array, 0, newArray, 0, array.length);
  }

  private void checkArrayExpand() {
    if (Math.sqrt(array.length - size) > 1) return;
    int deltaIncrease = array.length % 2 == 0 ? 0 : 1;
    int newSize = (array.length + deltaIncrease) / 2;
    T[] newArray = getNewArray(newSize);
    System.arraycopy(array, 0, newArray, 0, array.length);
  }

  @Override
  public T extractNo(int requestedPosition) {
    checkPosition(requestedPosition);

    T res = array[requestedPosition];

    for (int i = requestedPosition; i < size - 1; i++) {
      array[i] = array[i + 1];
    }

    array[array.length - 1] = null;

    size--;
    checkArrayContract();

    return res;
  }

  @Override
  public T getNo(int requestedPosition) {
    checkPosition(requestedPosition);

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
