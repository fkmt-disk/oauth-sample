BaseUrl = "#{location.origin}/#{(location.pathname.split '/').1}"

Api = do -> (
  
  onError = (xhr, status, error)!-> (
    console.log "[ApiCallFailed] #{xhr.status} : #{xhr.responseText}"
    console.log '[ApiCallFailed]', error
  )
  
  $.ajaxSetup {
    type: \GET
    dataType: \jsonp
    jsonp: \callback
    cache: false
    timeout: 6000
    error: onError
  }
  
  callApi = (name)-> (
    (data, success)!-> $.ajax {
      url: "#{BaseUrl}/service/#{name}"
      data
      success
    }
  )
  
  return {
    oauth2url: (callApi \oauth2url)
    login: (callApi \login)
  }
  
)
