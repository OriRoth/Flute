package util;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Collections {
  public <K, V> Map.Entry<K, V> entry(K key, V value) {
    return new AbstractMap.SimpleEntry<>(key, value);
  }

  @SafeVarargs
  public final <K, V> Map<K, V> map(Map.Entry<K, V>... entries) {
    Map<K, V> ret = new HashMap<>();
    Arrays.stream(entries).forEach(e -> ret.put(e.getKey(), e.getValue()));
    return ret;
  }
}
