
package com.mycompany.hashtable;

public class HashTable {
    private String[] keys;
    private String[] values;
    private int size;
    public HashTable(int size){
        this.size= size;
        keys = new String[size];
        values = new String[size];

    }

    private int hash(String key){
        int hash=0;
        int prime=31;
        for(int i=0; i < key.length();i++){
            hash= (hash*prime + key.charAt(i))%size;
        }
        return hash;
    }

    private int quadraticProb(String key, int attempt){
        int iniHash= hash(key);
        int index= (iniHash+ attempt*attempt)%size;
        return index;
    }

    public void put(String key, String value){
        int index = hash(key);
        if(keys[index] == null){
            keys[index] = key;
            values[index] = value;
        }else{
            int attempt = 1;
            int initIndex= index;
            while(keys[index] != null){
                index = quadraticProb(key, attempt);
                attempt++;
                if(index == initIndex){
                    rehash();
                    put(key, value);
                    return;
                }
            }
            keys[index] = key;
            values[index] = value;
        }
    }
    
    public String toString(){
        StringBuilder sb= new StringBuilder();
        for(int i =0; i< keys.length; i++){
            if(keys[i]!=null){
                sb.append("Indice ").append(i)
                .append(": ").append(keys[i])
                .append(" -> ").append(values[i]).append("\n");
            }
        }
        return sb.toString();
    }
    

    private void rehash(){
        int oldCapacity = size;
        int newCapacity= size*2;
        String[] oldKeys = keys;
        String[] oldValues = values;
        keys = new String[newCapacity];
        values = new String[newCapacity];
        size = newCapacity;

        for(int i=0; i< oldCapacity; i++){
            if(oldKeys[i] != null){
                put(oldKeys[i], oldValues[i]);
            }
        }
    }

    public String search(String key){
        int attempt=0;
        int hash=quadraticProb(key, attempt);
        int origHash= hash;
        while(keys[hash] != null && !keys[hash].equals(key)){
            attempt++;
            hash=quadraticProb(key, attempt);
            if(hash==origHash){
                return null;
            }
        }
        if(keys[hash] == null){
            return null;
        }else{
            return values[hash];
        }}

public boolean remove(String key) {
    int attempt = 0;
    int index = quadraticProb(key, attempt);
    int origHash = index;
    while (keys[index] != null && !keys[index].equals(key)) {
        attempt++;
        index = quadraticProb(key, attempt);
        if (index == origHash) {
            return false;
        }
    }
    if (keys[index] == null) {
        return false; 
    } else {
        keys[index] = null;
        values[index] = null;
        reinsert(index);
        return true;  
    }
}


    private void reinsert(int delIndex){
        int attempt=1;
        int i= ( delIndex+ attempt*attempt)%size;
        
        while (keys[i]!=null){
            String reKey= keys[i];
            String reValue= values[i];
            put(reKey, reValue);
            attempt++;
            i=( delIndex+ attempt*attempt)%size;
            
    }
}
    
    

}