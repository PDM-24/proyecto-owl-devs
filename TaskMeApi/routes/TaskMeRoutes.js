const express = require('express');
const router = express.Router();

const {
    registerUser,
    postTask,
    postReview,
    postCategory,
    postNotification,
    getUser,
    getAllUsersByCatgory,
    getAllTaskByRole,
    getAllReviewsByUser,
    getTaskById,
    getAllNotificationsByUser,
    getAllCategories,
    updateUser,
    updateTaskState,
    updateNotificationState,
    deleteNotification,
    getAllUsers,
    createChatPreview, 
    getChatPreviewsByUser
} = require('../controllers/TaskMeControllers');

/*RUTAS POST*/

router.post('/users/register', registerUser);                           //FUNCIONA     - conectada
router.post('/tasks', postTask);                                        //FUNCIONA     - conectada
router.post('/reviews', postReview);                                    //FUNCIONA     - conectada
router.post('/categories', postCategory);                               //FUNCIONA     - conectada
router.post('/notifications', postNotification);                        //FUNCIONA     - conectada
router.post('/chats', createChatPreview);                               //probando

/*RUTAS GET y login de usuario*/

router.post('/users/login', getUser);                                   //FUNCIONA     - conectada
router.get('/users/:categoryId', getAllUsersByCatgory);                 //FUNCIONA     - conectada
router.get('/tasks/role', getAllTaskByRole);                            //FUNCIONA
router.get('/reviews/:usuarioId', getAllReviewsByUser);                 //FUNCIONA     - conectada
router.get('/tasks/:taskId', getTaskById);                              //FUNCIONA     - conectada 
router.get('/notifications/:usuarioId', getAllNotificationsByUser);     //FUNCIONA     - conectada 
router.get('/categories', getAllCategories);                            //FUNCIONA     - conectada
router.get('/chats/:usuarioId', getChatPreviewsByUser);                    //probando

/*RUTAS PUT/PATCH*/

router.patch('/users/:usuarioId', updateUser);                                  //FUNCIONA     - conectada
router.patch('/tasks/:taskId/state', updateTaskState);                          //FUNCIONA
router.patch('/notifications/:notificationId/state', updateNotificationState);  //Probando

/*RUTAS DELETE*/

router.delete('/notifications/:notificationId', deleteNotification);


router.get('/users', getAllUsers);

module.exports = router;