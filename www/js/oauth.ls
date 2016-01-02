$ document .ready !-> (new OAuth).start!

OAuth = do -> (
  
  function OAuth then (
    @base-url = location.origin + '/' + (location.pathname.split '/').1
    @self-url = location.origin + location.pathname
    
    @params = location.search.slice 1 .split '&'
      |> _.map _, (-> it.split '=')
      |> _.object _
    
    void
  )
  
  OAuth::start = !-> (
    if @params.state? && @params.code? && not @params.error?
      then @handleCallback!
      else @initPage!
  )
  
  OAuth::initPage = !-> (
    $ \#login
      .click !~> Api.oauth2url redirect: @self-url, (!-> location.href = it.content)
      .show!
  )
  
  OAuth::handleCallback = !-> (
    $ \#message .show!
    
    data =
      state: @params.state
      code: @params.code
      redirect: @self-url
    
    Api.login data, !~> (
      sessionStorage.setItem 'name', it.content.name
      location.href = @base-url + '/html/top.html'
    )
  )
  
  return OAuth
  
)
