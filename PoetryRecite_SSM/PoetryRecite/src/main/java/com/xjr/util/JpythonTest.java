package com.xjr.util;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.tensorflow.*;

public class JpythonTest {
	
	public static void main(String[] args) {
        // TODO Auto-generated method stub
        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.execfile("C:\\Users\\Raffello\\Desktop\\peotry_generate-master\\peotry_generate-master\\eval.py");
        
        interpreter.execfile("C:\\Users\\Raffello\\Desktop\\test.py");
 
        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = (PyFunction)interpreter.get("test", PyFunction.class);
       
        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        PyObject pyobj = pyFunction.__call__(); 
        System.out.println("the poetry is: " + pyobj.toString());
    }

}
