// IBinderPool.aidl
package e4s0n.adr.chapter_2_server.aidl;

// Declare any non-default types here with import statements

interface IBinderPool {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    IBinder queryBinder(in int binderCode);
}
