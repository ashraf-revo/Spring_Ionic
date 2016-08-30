package org.revo.Util

/**
 * Created by revo on 7/29/16.
 */
class SoklinView {
    interface User {}

    interface Post {}


    interface UserPost {}

    interface PostUser {}

    interface PPU extends Post, PostUser, User {}

    interface Followers {}
}
