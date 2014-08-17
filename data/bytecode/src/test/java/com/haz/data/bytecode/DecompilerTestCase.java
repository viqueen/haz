/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.bytecode;

import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.haz.data.bytecode.cp.Class_Info;
import com.haz.data.codec.Codec;
import com.haz.data.codec.Factory;

/**
 * @author hasnaer
 *
 */
public class DecompilerTestCase {

  private Codec<ClassFile> classFileCodec;
  private DataInputStream classInputStream;
  
  @Before
  public void setUp () throws IOException {
    classFileCodec = Factory.create(ClassFile.class);
    classInputStream = new DataInputStream (getClass().getClassLoader().getResourceAsStream("Foo.class"));    
  }
  
  @Test
  public void testDecompile () throws IOException {
    assertNotNull(classInputStream);
    Optional<ClassFile> classFile = classFileCodec.decode(classInputStream);
    assertTrue(classFile.isPresent());    
    assertEquals(0xCAFEBABE, classFile.get().magic);
    assertEquals(classFile.get().constantPoolCount - 1, classFile.get().constantPool.length);
    int thisClass = classFile.get().thisClass;
    assertTrue(classFile.get().constantPool[thisClass].getInfo() instanceof Class_Info);
  }
}