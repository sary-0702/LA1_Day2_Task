package app.nunome.sary.challengeproduct

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookData (
    open var booktitle: String = "",
    open var auther: String = "",
    open var price : String = "",
    open var contents : String = ""
) : RealmObject()