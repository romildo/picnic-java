/*     / \____  _    _  ____   ______  / \ ____  __    _______
 *    /  /    \/ \  / \/    \ /  /\__\/  //    \/  \  //  /\__\   JΛVΛSLΛNG
 *  _/  /  /\  \  \/  /  /\  \\__\\  \  //  /\  \ /\\/ \ /__\ \   Copyright 2014-2018 Javaslang, http://javaslang.io
 * /___/\_/  \_/\____/\_/  \_/\__\/__/\__\_/  \_//  \__/\_____/   Licensed under the Apache License, Version 2.0
 */
package absyn;

import io.vavr.collection.Tree;
import parse.Loc;

public class TyInt extends Ty {

   public TyInt(Loc loc) {
      super(loc);
   }

   @java.lang.Override
   public Tree.Node<String> toTree() {
      return Tree.of("TyInt");
   }
}
