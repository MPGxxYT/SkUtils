package me.mortaldev.skutils.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.mortaldev.skutils.utils.AESUtil;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import javax.crypto.Cipher;
import java.security.Key;

public class ExrEncrypt extends SimpleExpression {
  private Expression<String> string, key;
  private int cipherMode;

  @Override
  protected String[] get(Event event) {
    try {
      Key generatedKey = AESUtil.generateKey(key.getSingle(event));

      if (cipherMode == Cipher.ENCRYPT_MODE) {
        return new String[]{AESUtil.encrypt(string.getSingle(event), generatedKey)};
      } else {
        return new String[]{AESUtil.decrypt(string.getSingle(event), generatedKey)};
      }
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
    if (parseResult.mark == 0) {
      cipherMode = Cipher.ENCRYPT_MODE;
    } else {
      cipherMode = Cipher.DECRYPT_MODE;
    }
    string = (Expression<String>) expressions[0];
    key = (Expression<String>) expressions[1];
    return true;
  }

  @Override
  public boolean isSingle() {
    return true;
  }

  @Override
  public Class<? extends String> getReturnType() {
    return String.class;
  }

  @Override
  public String toString(@Nullable Event event, boolean b) {
    return getClass().getName();
  }
}
