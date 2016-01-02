var gulp = require('gulp');
var del = require('del');
var jade = require('gulp-jade');
var lsc = require('gulp-livescript');
var uglify = require('gulp-uglify');
var less = require('gulp-less');
var mincss = require('gulp-minify-css');

gulp.task('clean', function() {
  del.sync(['src/main/webapp/html/*']);
  del.sync(['src/main/webapp/js/*']);
  del.sync(['src/main/webapp/css/*']);
});

gulp.task('compile-jade', function() {
  return gulp
    .src('www/html/**/*.jade')
    .pipe(jade({pretty:true}))
    .pipe(gulp.dest('src/main/webapp/html'));
});

gulp.task('compile-livescript', function() {
  del.sync(['www/js/**/*.js']);
  return gulp
    .src('www/js/**/*.ls')
    .pipe(lsc({bare:true}))
    .pipe(gulp.dest('www/js'))
    .pipe(uglify())
    .pipe(gulp.dest('src/main/webapp/js'));
});

gulp.task('compile-less', function() {
  del.sync(['www/css/**/*.css']);
  return gulp
    .src('www/css/**/*.less')
    .pipe(less())
    .pipe(gulp.dest('www/css'))
    .pipe(mincss())
    .pipe(gulp.dest('src/main/webapp/css'));
});

gulp.task('build', ['clean', 'compile-jade', 'compile-livescript', 'compile-less']);

gulp.task('watch', function() {
  gulp.watch(['www/**/*.jade'], ['compile-jade']);
  gulp.watch(['www/**/*.ls'], ['compile-livescript']);
  gulp.watch(['www/**/*.less'], ['compile-less']);
});

gulp.task('default', ['build']);
