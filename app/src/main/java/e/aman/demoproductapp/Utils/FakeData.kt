package e.aman.demoproductapp.Utils

import e.aman.demoproductapp.R
import e.aman.demoproductapp.model.Product

object FakeData {

    fun getProduct1() : Product{
        var colorList = ArrayList<String>()
        colorList.add("Red")
        colorList.add("Blue")
        colorList.add("Green")

        var storeList = ArrayList<String>()
        storeList.add("Store 1")
        storeList.add("Store 2")
        storeList.add("Store 3")


        return Product(1L , "Rolex Watch Mock 1" , "This is a test description",  280.0 , 200.0 , R.drawable.image_watch , colorList , storeList)
    }

    fun getProduct2() : Product{
        var colorList = ArrayList<String>()
        colorList.add("Red")
        colorList.add("Blue")
        colorList.add("Green")

        var storeList = ArrayList<String>()
        storeList.add("Store 1")
        storeList.add("Store 2")
        storeList.add("Store 3")


        return Product(2L , "Rolex Watch Mock 2" , "This is a test description",  280.0 , 200.0 , R.drawable.image_watch , colorList , storeList)
    }

    fun getProduct3() : Product{
        var colorList = ArrayList<String>()
        colorList.add("Red")
        colorList.add("Blue")
        colorList.add("Green")

        var storeList = ArrayList<String>()
        storeList.add("Store 1")
        storeList.add("Store 2")
        storeList.add("Store 3")


        return Product(3L , "Rolex Watch Mock 3" , "This is a test description",  280.0 , 200.0 , R.drawable.image_watch , colorList , storeList)
    }


}