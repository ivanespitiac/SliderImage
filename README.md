# SliderImage

Libreria de carrusel de imagenes por medio de una o mas String URL.
Desarrollada en kotlin 1.1.51, utilizando fresco para la carga de imagenes.

Slider image library for images with diferent URI types. 

Fresco Library reference (https://frescolib.org/docs/supported-uris.html)

```
TYPE	                SCHEME	                    FETCH METHOD USED
File on network	        http://, https://	        HttpURLConnection or network layer
File on device	        file://	                    FileInputStream
Content provider	    content://	                ContentResolver
Asset in app	        asset://	                AssetManager
Resource in app	        res:// as in res:///12345	Resources.openRawResource
Data in URI	            data:mime/type;base64,	    Following data URI spec (UTF-8 only)
```

Requere la habilitacion de la aceleracion por hardware.

```
android:hardwareAccelerated="true"
```

## Implementacion:

```
dependecies {
    implementation 'io.github.ivanespitiac:imageslider:1.0.3'
}
```
 
```
repositories {
    maven { url 'https://jitpack.io' }
}
```

## Uso:

Agrega el componente en la interfa grafica: (Add the component to UI)

```
<com.custom.sliderimage.logic.SliderImage
        android:id="@+id/slider"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```             

Agrega el component a nivel de codigo: (Add the component programatically)
  
```
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Create slider image component
        val sliderImage = SliderImage(this)
        
        // add images URLs
        val images = listOf(
        "url image",
        "url image",
        "url image")
        
        // Add image URLs to sliderImage
        slider.setItems(items)
        
        // Add slider component to a container
        container_main_images.addView(slider)
    }
```

Añadir tiempo para recorrer imagenes automaticamente: (Add timer to show images automatically.)

```
slider.addTimerToSlide(2000)
```

Remover tiempo añadido anteriormente: (Remove timer to show images)

```
slider.removeTimerSlide()
```

Inicializar el objeto SliderImage (Init SliderImage component programatically)

```
val slider = SliderImage(context)
```


lista de url de imagenes cargadas en el slider: (Get URLs array from component)

```
slider.getItems()
```

funciones porporcionadas por el slider:

```
slider.onPageListener(onPageScroll = { position, offSet, offSetPixels ->
            logD("position $position  offSet: $offSet  pixels $offSetPixels")
        }, onPageStateChange = { state ->
            logD("State change $state")
        }, onPageSelected = { position ->
            logD("page select $position")
        })
```

Lanzar full screen activity sin inicializar el component (view pager open full screen automatically when press page, but if you want to use only full screen image slider, you can use the next line):

```
SliderImage.openfullScreen(items = items, position = 0)
```

obtener propiedades del indicador (get circle indicator from 'me.relex:circleindicator:1.2.2@aar'):

```
slider.getIndicator()
```

## License

```
Copyright (C) 2014 relex

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

