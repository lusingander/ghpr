package com.github.lusingander.github

import java.lang.RuntimeException

class GraphQLRequestException(e: Throwable): RuntimeException(e)
