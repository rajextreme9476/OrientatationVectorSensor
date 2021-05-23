# OrientatationVectorSensor

Orientation Library for Android

Gradle dependency:

Add it in your root build.gradle at the end of repositories:

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  Step 2. Add the dependency

dependencies 
  {
	        implementation 'com.github.rajextreme9476:OrientatationVectorSensor:1.0'
	}



Usage : 

 Initialize RxOrientation in your Application's class onCreate

 RxOrientation rxOrientation = RxOrientation.getInstance(this);
        rxOrientation.getOrientationData().observe(this, new Observer<float[]>() {
            @Override
            public void onChanged(float[] floats) {

                log.d("RxOrientation",X = " + floats[0]+"\n\n\n"+"Y = " + floats[1]+"\n\n\n"+"Z = " + floats[2]);

            }
        });


![image](https://firebasestorage.googleapis.com/v0/b/rx0353.appspot.com/o/Screenshot_20210523-225938.jpg?alt=media&token=463d1f45-7ee8-4ad1-95e6-6335b1b59e8e)
