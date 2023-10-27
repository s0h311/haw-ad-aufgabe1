package application;

import java.util.ArrayList;

/**
 * Ein Wrapper für die ArrayList, so dass dieser das Interface application.SimpleList unterstützt.
 *
 * @param <T> der Daten-Typ der Elemente/Items/Datensätze, die die Liste aufnimmt.
 * @author Michael Schaefers ([UTF-8]:"Michael Schäfers");  Px@Hamburg-UAS.eu
 * @version 23/10/18#001
 */
public class ArrayListWrapper<T> implements SimpleList<T> {

  final private ArrayList<T> arrayList;


  /**
   * Der Konstruktor intialisiert ein Objekt vom Typ application.ArrayListWrapper.
   */
  public ArrayListWrapper() {
    arrayList = new ArrayList<T>();
  }//constructor()

  @Override
  public T extractNo(final int requestedPosition) {
    return arrayList.remove(requestedPosition);
  }//method()

  @Override
  public T getNo(final int requestedPosition) {
    return arrayList.get(requestedPosition);
  }//method()

  @Override
  public T setNo(final int requestedPosition, final T value) {
    return arrayList.set(requestedPosition, value);
  }//method()

  @Override
  public void putNo(final int requestedPosition, final T value) {
    arrayList.add(requestedPosition, value);
  }//method()

  @Override
  public int getSize() {
    return arrayList.size();
  }//method()

  @Override
  public boolean isEmpty() {
    return arrayList.isEmpty();
  }//method()

  @Override
  public void clear() {
    arrayList.clear();
  }//method()

}//class
