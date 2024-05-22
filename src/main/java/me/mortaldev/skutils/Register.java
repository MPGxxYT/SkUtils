package me.mortaldev.skutils;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import me.mortaldev.skutils.expressions.ExrEncrypt;

public class Register {
  public static void expressions(){
    Skript.registerExpression(ExrEncrypt.class, String.class, ExpressionType.PROPERTY, "skutils (0¦en|1¦de)crypt %string% with key %-string%");
  }
}
