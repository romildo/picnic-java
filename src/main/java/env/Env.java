package env;

import io.vavr.collection.List;
import types.*;

public class Env {

  public Table<Type> tenv;
  public Table<Entry> venv;

  public Env() {
    tenv = new Table<Type>();
    venv = new Table<Entry>();
  }

  private static <E> void put(Table<E> table, String name, E value) {
    table.put(name.intern(), value);
  }

   @Override
   public String toString() {
      return "Env{" +
         "tenv=" + tenv +
         ", venv=" + venv +
         '}';
   }

}
