# SliderImage

Libreria de carrusel de imagenes por medio de una o mas URL.

Desarrollada en kotlin 1.1.51, utilizando fresco para la carga de imagenes.

## Implementacion:

```
dependecies {
    implementation 'io.github.ivanespitiac:imageslider:1.0'
}
```
 
```
repositories {
    maven { url 'https://jitpack.io' }
}
```

## Uso:

```
<com.custom.sliderimage.logic.SliderImage
        android:id="@+id/slider"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```             
  
```
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val items = ArrayList<String>()
        items.add("url image")
        items.add("url image")
        slider.setItems(items)
    }
```

lista de url de imagenes cargadas en el slider:

```
slider.getItems()
```

funciones porporcionadas por el slider:

```
slider.onPageListener(
                onPageScroll = { i: Int, fl: Float, i1: Int ->
                    
                },
                onPageSelected = {position ->

                },
                onPageStateChange = {state ->

                })
```

Lanzar full screen activity aparte (view pager open full screen automatically when press page):

```
slider.openfullScreen(items = items, position = 0)
```

obtener propiedades del indicador (reference to 'me.relex:circleindicator:1.2.2@aar'):

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

