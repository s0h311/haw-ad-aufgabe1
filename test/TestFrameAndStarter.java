// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package test;

import application.ArrayListWrapper;
import application.SimpleList;
import test.supportStuff.OptimizationBlocker;
import utility.Beautician;

import java.util.HashSet;
import java.util.Set;


/**
 * TestFrame und Starter
 *
 * @author Michael Schaefers ([UTF-8]:"Michael Schäfers");  Px@Hamburg-UAS.eu
 * @version 23/10/18#001
 */
public class TestFrameAndStarter {

  public static void main(final String... unused) {

    //######################################################################
    //###
    //###   Acceptance Test
    //###
    System.out.printf("Start (acceptance) testing at: %s\n", Beautician.getPimpedTime());
    /*scope*/
    {
      /* Dies iet ein reiner Abnahmetest/Akzeptanztest!
       * Dieser Test hat NICHT zum Ziel bei der Fehlersuche zu unterstützen!
       * Fehlerfreien Code zu erstellen (und das schließt Testen mit ein)
       * liegt im Aufgabenbereich des Produzenten!
       * Oder hier konkret: Programmierers bzw. des jeweiligen Teams.
       */
      // setup test data for functionality test
      @SuppressWarnings("unchecked") final SimpleList<Integer>[] simpleList = (SimpleList<Integer>[]) new SimpleList[3];
      for (int i = 0; i < 3; i++) {
        simpleList[i] = new ArrayListWrapper<Integer>();                // <<<<<<<<<<<<<<<<================ HERE
      }//for
      final int listSize = 1657;
      for (int i = 0; i < listSize; i++) {
        simpleList[0].putNo(i, i);
        simpleList[2].putNo(i, i);
      }//for
      if (listSize != simpleList[0].getSize()) throw new AssertionError("ERROR detected");
      if (listSize != simpleList[2].getSize()) throw new AssertionError("ERROR detected");
      if (0 != simpleList[1].getSize()) throw new AssertionError("ERROR detected");
      //
      final SimpleList<Integer> refList = simpleList[2];
      SimpleList<Integer> srcList;
      SimpleList<Integer> dstList;


      // test functionality

      // test L>F (=)
      srcList = simpleList[0];
      dstList = simpleList[1];
      while (!srcList.isEmpty()) {
        dstList.putNo(0, srcList.extractNo(srcList.getSize() - 1));
      }//while
      // check
      if (refList.getSize() != dstList.getSize()) throw new AssertionError("ERROR detected");
      for (int i = 0; i < refList.getSize(); i++) {
        final int refValue = refList.getNo(i);
        final int dstValue = dstList.getNo(i);
        if (refValue != dstValue) throw new AssertionError("ERROR detected");
      }//for

      // test F>L (=)
      srcList = simpleList[1];
      dstList = simpleList[0];
      while (!srcList.isEmpty()) {
        dstList.putNo(dstList.getSize(), srcList.extractNo(0));
      }//while
      // check
      if (refList.getSize() != dstList.getSize()) throw new AssertionError("ERROR detected");
      for (int i = 0; i < refList.getSize(); i++) {
        final int refValue = refList.getNo(i);
        final int dstValue = dstList.getNo(i);
        if (refValue != dstValue) throw new AssertionError("ERROR detected");
      }//for

      // test F>F(><)
      srcList = simpleList[0];
      dstList = simpleList[1];
      while (!srcList.isEmpty()) {
        dstList.putNo(0, srcList.extractNo(0));
      }//while
      // check
      if (refList.getSize() != dstList.getSize()) throw new AssertionError("ERROR detected");
      for (int i = 0; i < refList.getSize(); i++) {
        final int refValue = refList.getNo(i);
        final int dstValue = dstList.getNo(dstList.getSize() - i - 1);
        if (refValue != dstValue) throw new AssertionError("ERROR detected");
      }//for

      // test L>L (><)
      srcList = simpleList[1];
      dstList = simpleList[0];
      while (!srcList.isEmpty()) {
        dstList.putNo(dstList.getSize(), srcList.extractNo(srcList.getSize() - 1));
      }//while
      // check
      if (refList.getSize() != dstList.getSize()) throw new AssertionError("ERROR detected");
      for (int i = 0; i < refList.getSize(); i++) {
        final int refValue = refList.getNo(i);
        final int dstValue = dstList.getNo(i);
        if (refValue != dstValue) throw new AssertionError("ERROR detected");
      }//for

      // test get&set
      for (int i = 0; i < refList.getSize(); i++) {
        dstList.setNo(i, refList.getNo(refList.getSize() - i - 1));
      }//for
      // check
      if (refList.getSize() != dstList.getSize()) throw new AssertionError("ERROR detected");
      for (int i = 0; i < refList.getSize(); i++) {
        final int refValue = refList.getNo(i);
        final int dstValue = dstList.getNo(dstList.getSize() - i - 1);
        if (refValue != dstValue) throw new AssertionError("ERROR detected");
      }//for

      // test rand->rand
      for (int stillToDo = 13; --stillToDo >= 0; ) {
        srcList = simpleList[0];
        dstList = simpleList[1];
        while (!srcList.isEmpty()) {
          final int remPosi = (int) (Math.random() * srcList.getSize());
          final int insPosi = (int) (Math.random() * (dstList.getSize() + 1));
          dstList.putNo(insPosi, srcList.extractNo(remPosi));
        }//while
        srcList = simpleList[1];
        dstList = simpleList[0];
        while (!srcList.isEmpty()) {
          final int remPosi = (int) (Math.random() * srcList.getSize());
          final int insPosi = (int) (Math.random() * (dstList.getSize() + 1));
          dstList.putNo(insPosi, srcList.extractNo(remPosi));
        }//while
      }//for
      // check
      Set<Integer> refSet = new HashSet<Integer>();
      for (int i = 0; i < refList.getSize(); i++) refSet.add(refList.getNo(i));
      Set<Integer> dstSet = new HashSet<Integer>();
      for (int i = 0; i < dstList.getSize(); i++) dstSet.add(dstList.getNo(i));
      if (!refSet.equals(dstSet)) throw new AssertionError("ERROR detected");
      if (listSize != dstList.getSize()) throw new AssertionError("ERROR detected");


      // test clear
      if (listSize != refList.getSize()) throw new AssertionError("ERROR detected");
      if (listSize != dstList.getSize()) throw new AssertionError("ERROR detected");
      if (0 != srcList.getSize()) throw new AssertionError("ERROR detected");
      for (int i = 0; i < 3; i++) {
        simpleList[i].clear();
        if (0 != simpleList[i].getSize()) throw new AssertionError("ERROR detected");
      }//for

      //
      //\=> all tests passed => code might be ok
    }//scope
    System.out.printf("\n\n");


    //######################################################################
    //###
    //###   Measurement
    //###
    System.out.printf("Start action/measurement at: %s\n", Beautician.getPimpedTime());
    System.out.printf("\n");
    //
    int runCnt;
    final OptimizationBlocker ob = new OptimizationBlocker();
    final SimpleList<Integer> list = new ArrayListWrapper<Integer>();


    // Am Listenanfang einfügen
    runCnt = 1_000_000;
    list.clear();
    /*scope*/
    {
      long duration = 0;
      for (int stillToDo = runCnt; --stillToDo >= 0; ) {
        final int someValue = runCnt;
        final long startTime = System.nanoTime();
        list.putNo(0, someValue);
        final long endTime = System.nanoTime();
        duration += (endTime - startTime);
      }//for
      for (int i = 0; i < list.getSize(); i++) ob.bo((int) (list.getNo(i)));

      System.out.printf("Am Listenanfang einfügen\n");
      System.out.printf("Measured computing time: %s\n", Beautician.nanoSecondBasedTimeToString(duration));
      System.out.printf("AVG: %s   run# %d\n", Beautician.nanoSecondBasedTimeToString(duration / runCnt), runCnt);
      System.out.printf("\n");
    }//scope


    // In der Listenmitte einfügen
    runCnt = 1_000_000;
    list.clear();
    /*scope*/
    {
      long duration = 0;
      for (int stillToDo = runCnt; --stillToDo >= 0; ) {
        final int someValue = runCnt;
        final int position = list.getSize() >>> 1;
        final long startTime = System.nanoTime();
        list.putNo(position, someValue);
        final long endTime = System.nanoTime();
        duration += (endTime - startTime);
      }//for
      for (int i = 0; i < list.getSize(); i++) ob.bo((int) (list.getNo(i)));

      System.out.printf("In der Listenmitte einfügen\n");
      System.out.printf("Measured computing time: %s\n", Beautician.nanoSecondBasedTimeToString(duration));
      System.out.printf("AVG: %s   run# %d\n", Beautician.nanoSecondBasedTimeToString(duration / runCnt), runCnt);
      System.out.printf("\n");
    }//scope


    // Am Listenende einfügen
    runCnt = 1_000_000;
    list.clear();
    /*scope*/
    {
      long duration = 0;
      for (int stillToDo = runCnt; --stillToDo >= 0; ) {
        final int someValue = runCnt;
        final int position = list.getSize();
        final long startTime = System.nanoTime();
        list.putNo(position, someValue);
        final long endTime = System.nanoTime();
        duration += (endTime - startTime);
      }//for
      for (int i = 0; i < list.getSize(); i++) ob.bo((int) (list.getNo(i)));

      System.out.printf("Am Listenende einfügen\n");
      System.out.printf("Measured computing time: %s\n", Beautician.nanoSecondBasedTimeToString(duration));
      System.out.printf("AVG: %s   run# %d\n", Beautician.nanoSecondBasedTimeToString(duration / runCnt), runCnt);
      System.out.printf("\n");
    }//scope


    // Am Listenanfang löschen
    runCnt = 1_000_000;
    list.clear();
    /*scope*/
    {
      for (int i = 0; i < runCnt; i++) list.putNo(0, i);
      long duration = 0;
      for (int stillToDo = runCnt; --stillToDo >= 0; ) {
        final long startTime = System.nanoTime();
        final int value = list.extractNo(0);
        final long endTime = System.nanoTime();
        duration += (endTime - startTime);
        ob.bo(value);
      }//for

      System.out.printf("Am Listenanfang löschen\n");
      System.out.printf("Measured computing time: %s\n", Beautician.nanoSecondBasedTimeToString(duration));
      System.out.printf("AVG: %s   run# %d\n", Beautician.nanoSecondBasedTimeToString(duration / runCnt), runCnt);
      System.out.printf("\n");
    }//scope


    // In der Listenmitte löschen
    runCnt = 1_000_000;
    list.clear();
    /*scope*/
    {
      for (int i = 0; i < runCnt; i++) list.putNo(0, i);
      long duration = 0;
      for (int stillToDo = runCnt; --stillToDo >= 0; ) {
        final int position = list.getSize() >>> 1;
        final long startTime = System.nanoTime();
        final int value = list.extractNo(position);
        final long endTime = System.nanoTime();
        duration += (endTime - startTime);
        ob.bo(value);
      }//for

      System.out.printf("In der Listenmitte löschen\n");
      System.out.printf("Measured computing time: %s\n", Beautician.nanoSecondBasedTimeToString(duration));
      System.out.printf("AVG: %s   run# %d\n", Beautician.nanoSecondBasedTimeToString(duration / runCnt), runCnt);
      System.out.printf("\n");
    }//scope


    // Am Listenende löschen
    runCnt = 1_000_000;
    list.clear();
    /*scope*/
    {
      for (int i = 0; i < runCnt; i++) list.putNo(0, i);
      long duration = 0;
      for (int stillToDo = runCnt; --stillToDo >= 0; ) {
        final int position = list.getSize() - 1;
        final long startTime = System.nanoTime() - 1;
        final int value = list.extractNo(position);
        final long endTime = System.nanoTime();
        duration += (endTime - startTime);
        ob.bo(value);
      }//for

      System.out.printf("Am Listenende löschen\n");
      System.out.printf("Measured computing time: %s\n", Beautician.nanoSecondBasedTimeToString(duration));
      System.out.printf("AVG: %s   run# %d\n", Beautician.nanoSecondBasedTimeToString(duration / runCnt), runCnt);
      System.out.printf("\n");
    }//scope


    System.out.printf("\n\n");
    System.out.printf("Just show state/computed signature of OptimizationBlocker to prevent (removing unnecessary code) optimization:  OB->%08x  (ignore)\n", ob.getSignature());
    System.out.flush();
  }//method()

}//class
