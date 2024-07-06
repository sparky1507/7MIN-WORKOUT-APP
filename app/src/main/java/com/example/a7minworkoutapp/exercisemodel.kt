package com.example.a7minworkoutapp

class exercisemodel(
    private var id:Int,
    private var name:String,
    private var image:Int,
    private  var isCompleted:Boolean,
    private var isSelected:Boolean
) {
    fun setid(id: Int){
        this.id=id
    }
    fun getid(): Int {
        return id
    }
    fun setimage(image: Int){
        this.image=image
    }
    fun getimage(): Int {
        return image
    }
    fun setname(name:String){
        this.name=name
    }
    fun getname():String{
        return name
    }
    fun setisCompleted(isCompleted: Boolean){
        this.isCompleted=isCompleted
    }
    fun getisCompleted():Boolean{
        return isCompleted
    }
    fun setisSelected(isSelected: Boolean){
        this.isSelected=isSelected
    }
    fun getisSelected():Boolean{
        return isSelected
    }
}