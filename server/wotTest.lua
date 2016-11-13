-- please crate your own WiFi settings here
wifi.setmode(wifi.STATION)
wifi.sta.config("CampusUniPassau","") -- your wifi settings

-- setup CoAP server
cs=coap.Server()
cs:listen(5683)

-- register resources 


temp=20
cs:var(coap.GET, "temp",50)

file.open("td.JSONLD")
td=file.read()
cs:var(coap.GET, "td",50)
file.close()


  