package com.pb.sawdust.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The {@code ContainsMetadataImpl} provides a basic implementation of the {@code ContainsMetadata} interface, backed
 * by a {@code HashMap}.
 *
 * @param <K>
 *        The type of the metadata keys. 
 *
 * @author crf <br/>
 *         Started: Dec 14, 2009 12:11:09 PM
 */
public class ContainsMetadataImpl<K> implements ContainsMetadata<K> {
    private Map<K,Object> metadata = new HashMap<K,Object>();

    @Override
    public int metadataSize() {
        return metadata.size();
    }

    @Override
    public Set<K> getMetadataKeys() {
        return metadata.keySet();
    }

    @Override
    public boolean containsMetadataKey(K key) {
        return metadata.containsKey(key);
    }

    @Override
    public Object getMetadataValue(K key) {
        if (!containsMetadataKey(key))
            throw new IllegalArgumentException("Metadata key not found: " + key);
        return metadata.get(key);
    }

    @Override
    public void setMetadataValue(K key, Object value) {
        metadata.put(key,value);
    }

    @Override
    public Object removeMetadataElement(K key) {
        return metadata.remove(key);
    }
}