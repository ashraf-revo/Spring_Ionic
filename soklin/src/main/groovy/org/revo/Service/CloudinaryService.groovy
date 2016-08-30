package org.revo.Service

/**
 * Created by ashraf on 3/27/2016.
 */
interface CloudinaryService {
    String Upload(String file)

    String Upload(byte[] file)
}