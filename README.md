# ethervine

A [re-frame](https://github.com/Day8/re-frame) application. #buidl

## Development Mode

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:6777](http://localhost:6777).

## Production Build


To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```
