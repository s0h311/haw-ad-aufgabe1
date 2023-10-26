// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse

/**
 * Eine einfache Liste mit den elementar nötigen Operationen.
 * Zwar wäre es ausdrücklich besser java.util.List zu unterstützen, aber dies würde
 * den Schwierigkeitsgrad der Aufgabe vermutlich stark erhöhen.
 * Analog zu bzw. im Einklang mit java.util.List gilt für eine Liste die n Datensätze enthält:
 * Der "erste" Datensatz hat die Datensatz-Position 0.
 * Der "letzte" Datensatz hat die Datensatz-Position n-1.
 * Sofern ein Datensatz eingefügt werden soll, steht die Einfüge-Position
 * 0 für "am Anfang der Liste einfügen",
 * n für "am Ende der Liste einfügen"
 * x für "vor dem Datensatz mit der Datensatz-Position x einfügen".
 * Bemerkung: Es wäre schön, wenn ungültige Positionsangaben eine IllegalArgumentException auslösen.
 * <p>
 * Ebenso wie die Positionsangaben sind auch die eingeforderten Operationen (in ihrer "reduzierten" Form)
 * im Einklang mit den jeweilig entsprechenden Operationen in java.util.List.
 * <p>
 * Die "Sicherheitsdenke" - keine Referenzen auf Interna zuzulassen - muss nicht unterstützt werden.
 * Es wäre aber schön, wenn Sie dies dennoch tun ;-)
 *
 * @param <T> der Daten-Typ der Elemente/Items/Datensätze, die die Liste aufnimmt.
 * @author Michael Schaefers ([UTF-8]:"Michael Schäfers");  Px@Hamburg-UAS.eu
 * @version 23/10/18#001
 */
public interface SimpleList<T> {

  /**
   * Dies Operation unterstützt das Extrahieren eines Datensatzes von der als Parameter übergebenen Datensatz-Position.
   * Extrahieren bedeutet, dass der jeweilige Datensatz als Ergebnis abgeliefert und aus der Liste entfernt wird
   * sowie dass alle nachfolgenden Datensätze entsprechend "aufrücken",
   * sofern die Positionsangabe gültig ist.
   * Es wäre schön, wenn ungültige Positionsangaben eine IllegalArgumentException auslösen.
   *
   * @param requestedPosition die Datensatz-Position des zu extrahierenden Datensatzes in der Liste.
   * @return der Wert des extrahierten Datensatzes.
   */
  T extractNo(final int requestedPosition);

  /**
   * Dies Operation unterstützt den lesenden Zugriff auf einen Datensatz von der als Parameter übergebenen Datensatz-Position.
   * Der jeweilige Datensatz wird als Ergebnis abgeliefert und verbleibt in der Liste
   * Es wäre schön, wenn ungültige Positionsangaben eine IllegalArgumentException auslösen.
   * Die "Sicherheitsdenke" keine Referenzen auf Interna zuzulassen
   * bzw. eine Kopie des abzuliefernden Datensatzes zu erstellen muss nicht unterstützt werden.
   * Es wäre aber schön, wenn Sie dies dennoch tun ;-)
   *
   * @param requestedPosition die Datensatz-Position des jeweilgen Datensatz, der "gelesen" werden soll.
   * @return der Wert des ausgelesenen Datensatzes.
   */
  T getNo(final int requestedPosition);

  /**
   * Dies Operation unterstützt den schreibenden Zugriff auf einen Datensatz an der als Parameter übergebenen Datensatz-Position.
   * Der jeweilig ausgewählte Datensatz wird mit dem als Parameter übergebenen Wert value überschrieben.
   * Der alte Wert wird als Rückgabewert abgeliefert.
   * Es wäre schön, wenn ungültige Positionsangaben eine IllegalArgumentException auslösen.
   * Die "Sicherheitsdenke" keine Referenzen auf Interna zuzulassen
   * bzw. eine Kopie von value oder des abzuliefernden alten Datensatz-Wertes zu erstellen muss nicht unterstützt werden.
   * Es wäre aber schön, wenn Sie dies dennoch tun ;-)
   *
   * @param requestedPosition die Datensatz-Position des auszulesenden Datensatzes.
   * @param value             der neue Wert des zu überschreibenden Datensatzes.
   * @return der alte Wert des auszulesenden Datensatzes.
   */
  T setNo(final int requestedPosition, final T value);

  /**
   * Dies Operation unterstützt das Einfügen eines Datensatzes in die Liste an der als Parameter übergebenen Einfüge-Position.
   * Es wäre schön, wenn ungültige Positionsangaben eine IllegalArgumentException auslösen.
   * Die "Sicherheitsdenke" keine Referenzen auf Interna zuzulassen
   * bzw. eine Kopie von value zu erstellen muss nicht unterstützt werden.
   * Es wäre aber schön, wenn Sie dies dennoch tun ;-)
   *
   * @param requestedPosition die Einfüge-Position des einzufügenden Datensatzes.
   */
  void putNo(final int requestedPosition, final T value);

  /**
   * Diese Operation liefert die Anzahl der Datensätze in der Liste.
   *
   * @return die Anzahl der Datensätze in der Liste.
   */
  int getSize();

  /**
   * Diese Operation gibt Auskunft, ob die Liste leer ist bzw. keine Datensätze enthält.
   *
   * @return true, falls sie Liste leer ist sondt false.
   */
  boolean isEmpty();

  /**
   * Diese Operation entfernt alle Datensätze aus der Liste.
   * "Danach" ist die Liste leer und enhält keine ELemente mehr.
   */
  void clear();

}//Interface
