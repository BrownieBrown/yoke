package mbraun.yoke.exception

class ResourceNotFoundException(message: String) : RuntimeException(message) {
    companion object {
        private const val serialVersionUID = 1L
    }
}