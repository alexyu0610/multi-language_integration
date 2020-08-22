abstract class StringOrInt {
    abstract public byte[] java_string_xor (byte[] str1);
    } 
    
class StringOrInt_IsString extends StringOrInt {
    public byte[] my_string;
    public StringOrInt_IsString (byte[] s) { my_string = s; }
    public byte[] java_string_xor (byte[] plain) {
        byte [] cypher = new byte[plain.length];
    for (int i = 0; i < plain.length; i++)
        cypher[i] = (byte) (plain[i] ^ my_string[i]); return cypher;
        } 
}


// Tell java about Native method
static {
    // load native library
    System.loadLibrary("cjava");
}

private static native byte[]
    c_string_xor(byte[] plain, StringOrInt key);


// C code with JNI
JNIEXPORT jbyteArray JNICALL Java_StringXOR_c_1string_1xor (JNIEnv * env, jclass self, jbyteArray jplain, jobject jkey) {
    jbyte * n_plain = (*env)->GetByteArrayElements(env, jplain, NULL);
    size_t plainsize = (*env)->GetArrayLength(env, j_plain);
    jclass key_cls = (*env)->GetObjectClass(env, jkey);
    jfieldID fid;
    int i;
    jbyteArray jcypher = (*env)->NewByteArray(env,plainsize);
    jbyte * n_cypher = (*env)->GetByteArrayElements(env, jcypher, NULL);
    fid = (*env)->GetFieldID(env, key_cls, "my_int", "I"); 
    if (fid != NULL) {
        // int field
        jint n_mask = (*env)->GetIntField(env, jkey, fid);
        for (i=0;i<plainsize;i++) {
            n_cypher[i] = n_plain[i] ^ n_mask; }
    } else {
        fid = (*env)->GetFieldID(env, key_cls, "my_string", "[B");
        if (fid != NULL) {
            // string field
            jbyteArray jkeyt = (*env)->GetObjectField(env, jkey, fid); 
            jbyte * n_keytext = (*env)->GetByteArrayElements (env, jkeyt, NULL);
            for (i=0;i<plainsize;i++)
            cypher[i] = n_plain[i] ^ n_keytext[i]; (*env)->ReleaseByteArrayElements(env,jkeyt,n_keytext,0);
            }
    }
    // Garbage collector potential pitfall
    (*env)->ReleaseByteArrayElements(env, jplain, n_plain, 0);
    (*env)->ReleaseByteArrayElements(env, jcypher, n_cypher, 0);
    return jcypher;
}

// Compiling, Linking and Running JNI
gcc -I $(JAVA)/include \
    -o libcjava.so -shared -fPIC cjava.c 
javac StringXOR.java 
java -Djava.library.path=. StringXOR
// Can also use javap to automatically generate header files for C JNI implementations